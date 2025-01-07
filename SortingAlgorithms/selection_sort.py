def selection_sort(array_to_sort: list[int]) -> list[int]:
    sorted_indices = 0
    while True:
        min_element = float('inf')
        array_changed = False
        min_index = sorted_indices

        for i in range(sorted_indices, len(array_to_sort)):
            if array_to_sort[i] < min_element:
                min_index = i
                min_element = array_to_sort[i]
                array_changed = True

        if array_changed == False:
            return array_to_sort

        array_to_sort[sorted_indices], array_to_sort[min_index] = array_to_sort[min_index], array_to_sort[sorted_indices]
        sorted_indices += 1
        array_changed = False


if __name__ == "__main__":
    print(selection_sort([]))
    print(selection_sort([1]))
    print(selection_sort([12, 17, 9, 42, 1337, 29, 1, 5]))
