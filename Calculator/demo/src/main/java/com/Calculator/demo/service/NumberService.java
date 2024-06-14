package com.Calculator.demo.service;

import com.Calculator.demo.model.NumberResponse; // Assuming the package name and class name are correct
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class NumberService {

    private final Map<String, String> apiEndpoints = Map.of(
        "p", "http://20.244.56.144/test/primes",
        "f", "http://20.244.56.144/test/fibo",
        "e", "http://20.244.56.144/test/even",
        "r", "http://20.244.56.144/test/rand"
    );

    private final RestTemplate restTemplate = new RestTemplate();
    private final int windowSize = 10;
    private final LinkedList<Integer> window = new LinkedList<>();

    public NumberResponse getNumbers(String numberId) {
        if (!apiEndpoints.containsKey(numberId)) {
            throw new IllegalArgumentException("Invalid number ID");
        }

        String url = apiEndpoints.get(numberId);
        List<Integer> numbers = fetchNumbersFromApi(url);

        List<Integer> windowPrevState = new ArrayList<>(window);

        for (Integer number : numbers) {
            if (!window.contains(number)) {
                if (window.size() >= windowSize) {
                    window.poll();
                }
                window.add(number);
            }
        }

        List<Integer> windowCurrState = new ArrayList<>(window);
        double avg = windowCurrState.stream().mapToInt(Integer::intValue).average().orElse(0);

        NumberResponse response = new NumberResponse();
        response.setWindowPrevState(windowPrevState);
        response.setWindowCurrState(windowCurrState);
        response.setAvg(avg);

        return response;
    }

    private List<Integer> fetchNumbersFromApi(String url) {
        try {
            Map<String, List<Integer>> response = restTemplate.getForObject(url, Map.class);
            return response.get("numbers");
        } catch (Exception e) {
            // Handle exceptions (e.g., timeout, error response)
            return Collections.emptyList();
        }
    }
}
