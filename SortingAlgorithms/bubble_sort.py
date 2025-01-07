def bubble_sort(array_to_sort: list[int]) -> list[int]:
    while True:
        array_changed = False

        for i in range(len(array_to_sort)-1):
            if array_to_sort[i+1] < array_to_sort[i]:
                array_to_sort[i], array_to_sort[i+1] = array_to_sort[i+1], array_to_sort[i]
                array_changed = True

        if array_changed == False:
            break

    return array_to_sort


if __name__ == "__main__":
    print(bubble_sort([]))
    print(bubble_sort([2]))
    print(bubble_sort([45, 18, 42, 99, 10, 7, 96, 130, 75]))
