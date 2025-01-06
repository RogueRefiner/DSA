from collections import defaultdict


def counting_sort_default_dict(array_to_sort: list[any]) -> list[any]:
    d = defaultdict(int)
    sorted_array = []
    for num in array_to_sort:
        d[num] += 1

    for key in sorted(d):
        sorted_array.extend([key]*d[key])

    return sorted_array


def counting_sort(array_to_sort: list[int]) -> list[int]:
    if len(array_to_sort) == 0:
        return array_to_sort

    count_array = [0 for _ in range(max(array_to_sort))]
    sorted_array = []
    for num in array_to_sort:
        count_array[num-1] += 1

    for idx, count in enumerate(count_array):
        sorted_array.extend([idx+1]*count)
    return sorted_array


if __name__ == "__main__":
    print(counting_sort_default_dict([]))
    print(counting_sort([]))

    print(counting_sort_default_dict([2, 3, 10, 2, 7, 4, 7, 7]))
    print(counting_sort([2, 3, 10, 2, 7, 4, 7, 7]))

    # print(counting_sort_default_dict([False, True, False, True]))
    # print(counting_sort_default_dict(["a", "b", "a", "c", "d"]))
    # print(counting_sort_default_dict([2.0, 3.0, 10.0, 2.0, 7.0, 4.0, 7.0, 7.0]))
