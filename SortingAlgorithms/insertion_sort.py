def insertion_sort(array_to_sort: list[int]) -> list[int]:
    next_unsorted_index = 1

    while next_unsorted_index <= len(array_to_sort) - 1:
        value_to_sort = array_to_sort[next_unsorted_index]
        swap_index = next_unsorted_index - 1

        while swap_index >= 0 and array_to_sort[swap_index] > value_to_sort:
            array_to_sort[swap_index+1] = array_to_sort[swap_index]
            swap_index -= 1

        array_to_sort[swap_index+1] = value_to_sort
        next_unsorted_index += 1

    return array_to_sort


if __name__ == "__main__":
    print(insertion_sort([]))
    print(insertion_sort([1]))
    print(insertion_sort([12, 17, 9, 42, 1337, 29, 1, 5]))
    print(insertion_sort([14, 33, 27, 35, 24, 19, 42, 44, 27]))
    print(insertion_sort([33, 14, 27, 10, 35, 19, 44, 42]))
