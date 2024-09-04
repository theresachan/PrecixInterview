from collections import Counter
import heapq

def findTopKFrequentElements(nums, k):
    # Step 1: Calculate the frequency of each element in the array
    frequency_map = Counter(nums)
    
    # Step 2: Extract the top k elements based on their frequency
    most_frequent = heapq.nlargest(k, frequency_map.keys(), key=frequency_map.get)
    
    return most_frequent

# Example 1:
numbers = [1, 1, 1, 2, 2, 3]
k_value = 2
print(findTopKFrequentElements(numbers, k_value))  # Output: [1, 2]

# Example 2:
numbers = [1]
k_value = 1
print(findTopKFrequentElements(numbers, k_value))  # Output: [1]
