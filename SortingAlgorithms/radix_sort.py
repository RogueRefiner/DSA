def radix_sort(array_to_sort: list[int]) -> list[int]:
    if len(array_to_sort) == 0:
        return array_to_sort

    highest_order_of_magnitude = len(str(max(array_to_sort)))
    index_of_least_significant_digit = highest_order_of_magnitude-1

    array_to_sort = [str(num) for num in array_to_sort]

    for idx, num in enumerate(array_to_sort):
        if len(num) < highest_order_of_magnitude:
            array_to_sort[idx] = num.zfill(highest_order_of_magnitude)

    for _ in range(highest_order_of_magnitude):
        radix_array = [[] for _ in range(10)]
        for num in array_to_sort:
            least_significant_digit = num[index_of_least_significant_digit]
            radix_array[int(least_significant_digit)].append(num)

        array_to_sort = [lst for sublist in radix_array for lst in sublist]
        index_of_least_significant_digit -= 1

    return [int(num) for num in array_to_sort]


if __name__ == "__main__":
    print(radix_sort([]))
    print(radix_sort([33]))
    print(radix_sort([33, 45, 40, 25, 17, 24]))
    print(radix_sort([7, 61, 5, 100, 24, 137, 42, 1, 1337, 9, 90, 1000]))
