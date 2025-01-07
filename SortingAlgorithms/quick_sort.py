def quick_sort(array_to_sort: list[int]) -> list[int]:
    if len(array_to_sort) < 2:
        return array_to_sort

    pivot_idx = 0
    pivot_element = array_to_sort[pivot_idx]
    left_ptr = pivot_idx + 1
    right_ptr = len(array_to_sort) - 1

    while left_ptr <= right_ptr:
        if array_to_sort[right_ptr] >= pivot_element:
            right_ptr -= 1
            continue

        if array_to_sort[left_ptr] < pivot_element:
            left_ptr += 1
            continue

        if array_to_sort[left_ptr] > pivot_element and array_to_sort[right_ptr] < pivot_element:
            array_to_sort[left_ptr], array_to_sort[right_ptr] = array_to_sort[right_ptr], array_to_sort[left_ptr]

    array_to_sort[pivot_idx], array_to_sort[right_ptr] = array_to_sort[right_ptr], array_to_sort[pivot_idx]

    left_sorted = quick_sort(array_to_sort[:right_ptr])
    right_sorted = quick_sort(array_to_sort[left_ptr:])

    return left_sorted + [array_to_sort[right_ptr]] + right_sorted


if __name__ == "__main__":
    print(quick_sort([]))
    print(quick_sort([6]))
    print(quick_sort([6, 3, 9, 8, 4, 6, 5, 2]))
