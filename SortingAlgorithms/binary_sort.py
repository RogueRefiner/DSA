def binary_search(array_to_sort: list[int], element_to_find: int) -> bool:
    if len(array_to_sort) == 0:
        return False

    if len(array_to_sort) == 1 and array_to_sort[0] != element_to_find:
        return False

    middle = len(array_to_sort)//2

    if element_to_find == array_to_sort[middle]:
        return True
    elif element_to_find < array_to_sort[middle]:
        return binary_search(array_to_sort[:middle], element_to_find)
    else:
        return binary_search(array_to_sort[middle:], element_to_find)


if __name__ == "__main__":
    print(binary_search([1, 3, 5, 6, 7, 9], 6))  # True
    print(binary_search([1, 3, 5, 7, 9], 5))  # True
    print(binary_search([1, 3, 5, 7, 9], 1))  # True
    print(binary_search([1, 3, 5, 7, 9], 9))  # True
    print(binary_search([1, 3, 5, 7, 9], 4))  # False
    print(binary_search([], 5))  # False
    print(binary_search([5], 5))  # True
    print(binary_search([5], 10))  # False
    print(binary_search([1, 3, 3, 3, 5], 3))  # True
    print(binary_search([7, 7, 7, 7, 7], 7))  # True
    print(binary_search(list(range(1, 10001)), 5000))  # True
    print(binary_search(list(range(1, 10000)), 9999))  # True
    print(binary_search([-10, -5, 0, 5, 10], 0))  # True
    print(binary_search([1, 2, 3, 4, 5], 6))  # False
    print(binary_search([1, 2, 3, 4, 5], 0))  # False
    print(binary_search([9, 7, 5, 3, 1], 5))  # True
    print(binary_search([1000000000, 2000000000, 3000000000, 4000000000], 3000000000))  # True
