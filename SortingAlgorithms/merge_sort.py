def merge_sort(array_to_sort: list[int | None]) -> list[int]:
    if len(array_to_sort) <= 1:
        return array_to_sort

    middle = len(array_to_sort) // 2
    left_array = array_to_sort[:middle]
    right_array = array_to_sort[middle:]

    left_array_sorted = merge_sort(left_array)
    right_array_sorted = merge_sort(right_array)

    return merge(left_array_sorted, right_array_sorted)


def merge(left_sorted: list[int | None], right_sorted: list[int | None]) -> list[int]:
    if len(left_sorted) == 0:
        return right_sorted
    elif len(right_sorted) == 0:
        return left_sorted
    elif left_sorted[0] < right_sorted[0]:
        return [left_sorted[0]] + merge(left_sorted[1:], right_sorted)
    else:
        return [right_sorted[0]] + merge(left_sorted, right_sorted[1:])


if __name__ == "__main__":
    print(merge_sort([]))
    print(merge_sort([23, 42, 1337, 98, 1]))
    print(merge_sort([23, 42, 1337, 98, 1, 75]))
