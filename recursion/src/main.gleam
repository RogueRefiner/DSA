import gleam/bool
import gleam/int
import gleam/io
import gleam/string

//import recursive.{type Tree, add_to_tree}
import recursive

pub fn sum(list: List(Int), total: Int) -> Int {
  case list {
    [head, ..tail] -> sum(tail, total + head)
    [] -> total
  }
}

pub fn reverse(str: List(String)) -> List(String) {
  reverse_loop(str, [])
}

fn reverse_loop(remaining_list: List(String), a: List(String)) -> List(String) {
  case remaining_list {
    [] -> a
    [head, ..tail] -> reverse_loop(tail, [head, ..a])
  }
}

pub fn reverse_string(list: List(String)) -> String {
  reverse_string_loop(list, "")
}

fn reverse_string_loop(remaining_list: List(String), str: String) -> String {
  case remaining_list {
    [] -> str
    [head, ..tail] ->
      reverse_string_loop(tail, string.append(to: head, suffix: str))
  }
}

pub fn palindrome(list: List(String)) -> Bool {
  list == reverse(list)
}

pub fn palindrome_by_loop(list: List(String)) -> Bool {
  palindrome_loop(list, reverse(list), False)
}

fn palindrome_loop(
  remaining_list: List(String),
  remaining_list_reversed: List(String),
  is_palindrome: Bool,
) -> Bool {
  case remaining_list {
    [] -> is_palindrome
    [head, ..tail] -> {
      case remaining_list_reversed {
        [] -> is_palindrome
        [end, ..rest] -> {
          case head == end {
            True -> palindrome_loop(tail, rest, True)
            False -> False
          }
        }
      }
    }
  }
}

pub fn find_max(list: List(Int)) -> Int {
  find_max_loop(list, 0)
}

fn find_max_loop(remaining_list: List(Int), max: Int) -> Int {
  case remaining_list {
    [] -> max
    [head, ..tail] ->
      case head > max {
        True -> find_max_loop(tail, head)
        False -> find_max_loop(tail, max)
      }
  }
}

pub fn find(list: List(String), char_to_find: String) -> Bool {
  find_loop(list, char_to_find)
}

fn find_loop(remaining_list: List(String), char_to_find: String) -> Bool {
  case remaining_list {
    [] -> False
    [head, ..tail] ->
      case head == char_to_find {
        True -> True
        False -> find_loop(tail, char_to_find)
      }
  }
}

pub fn find_substring(
  list: List(String),
  substring_to_find: List(String),
) -> Bool {
  find_substring_loop(list, substring_to_find, False)
}

fn find_substring_loop(
  remaining_list: List(String),
  remaining_substring_to_find: List(String),
  found: Bool,
) -> Bool {
  case remaining_list {
    [] -> found
    [head, ..tail] -> {
      case remaining_substring_to_find {
        [] -> found
        [start, ..end] -> {
          case head == start {
            True -> find_substring_loop(tail, end, True)
            False ->
              find_substring_loop(tail, remaining_substring_to_find, False)
          }
        }
      }
    }
  }
}

pub fn print_nested_list(nested_list: List(List(String))) -> Nil {
  case nested_list {
    [] -> Nil
    [head, ..tail] -> {
      io.debug(head)
      print_nested_list(tail)
    }
  }
}

pub fn iterate_over_nested_lists(nested_list: List(List(String))) -> Nil {
  case nested_list {
    [] -> Nil
    [head, ..tail] -> {
      iterate_loop(head)
      iterate_over_nested_lists(tail)
    }
  }
}

fn iterate_loop(list: List(String)) -> Nil {
  case list {
    [] -> Nil
    [head, ..tail] -> {
      io.debug(head)
      iterate_loop(tail)
    }
  }
}

pub fn main() {
  let list: List(Int) = [1, 2, 3, 4, 5, 4, 3, 2, 1]

  let str: String = "Hello World!"
  let string_list: List(String) = string.split(str, on: "")

  let palindrome_str: String = "noon"
  let palindrome_list: List(String) = string.split(palindrome_str, on: "")

  let nested_list: List(List(String)) = [
    ["H", "E", "L", "L", "O"],
    ["W", "O", "R", "L", "D"],
    ["!"],
  ]

  io.debug("Palindrome_list: " <> string.concat(palindrome_list))
  io.debug("Sum: " <> int.to_string(sum(list, 0)))
  io.debug("Reversed String: " <> reverse_string(string_list))
  io.debug("Find maximum: " <> int.to_string(find_max(list)))
  io.debug("Is Palindrome: " <> bool.to_string(palindrome(string_list)))
  io.debug("Is Palindrome: " <> bool.to_string(palindrome(palindrome_list)))
  io.debug(
    "Is Palindrome By Loop: " <> bool.to_string(palindrome_by_loop(string_list)),
  )
  io.debug(
    "Is Palindrome By Loop: "
    <> bool.to_string(palindrome_by_loop(palindrome_list)),
  )
  io.debug("Find: " <> bool.to_string(find(string_list, "H")))
  io.debug("Find: " <> bool.to_string(find(string_list, "!")))
  io.debug("Find: " <> bool.to_string(find(string_list, "Z")))
  io.debug(
    "Find Substring: "
    <> bool.to_string(find_substring(string_list, string.split("Hello", on: ""))),
  )
  io.debug(
    "Find Substring: "
    <> bool.to_string(find_substring(string_list, string.split("olleH", on: ""))),
  )
  io.debug(
    "Find Substring: "
    <> bool.to_string(find_substring(string_list, string.split("World", on: ""))),
  )
  io.debug(
    "Find Substring: "
    <> bool.to_string(find_substring(string_list, string.split("Hel", on: ""))),
  )
  io.debug(
    "Find Substring: "
    <> bool.to_string(find_substring(
      string_list,
      string.split("Hello World!", on: ""),
    )),
  )

  io.debug("Print_nested_list: ")
  print_nested_list(nested_list)

  io.debug("iterate_over_nested_lists: ")
  iterate_over_nested_lists(nested_list)

  io.debug("The recursive book of recursion:")
  io.debug(
    "Chapter 2: Factorial: " <> int.to_string(recursive.ch2_factorial(5)),
  )

  io.debug(
    "Chapter 2: Sum Series: " <> int.to_string(recursive.ch2_sum_series(5)),
  )

  io.debug(
    "Chapter 2: 2**3: "
    <> int.to_string(recursive.ch2_sum_powers_of_2(3))
    <> " 2**2: "
    <> int.to_string(recursive.ch2_sum_powers_of_2(2))
    <> " 2**1: "
    <> int.to_string(recursive.ch2_sum_powers_of_2(1)),
  )

  io.debug(
    "Chapter 3: Concat: " <> recursive.ch3_concat(["Hello", "World", "!"]),
  )

  io.debug(
    "Chapter 3: Product: Empty List: "
    <> int.to_string(recursive.ch3_product([]))
    <> " Non Empty List: "
    <> int.to_string(recursive.ch3_product([2, 3, 4, 10])),
  )

  let tree =
    recursive.Leaf
    |> recursive.insert(10)
    |> recursive.insert(5)
    |> recursive.insert(15)
    |> recursive.insert(3)
    |> recursive.insert(7)
    |> recursive.insert(12)
    |> recursive.insert(18)

  io.println("Chapter 4: Reverse Inorder Traversal: ")
  recursive.ch4_reverse_inorder_traverse(tree)

  io.println("Starting Tree: " <> recursive.to_string_tree(tree))

  let expaned_tree = recursive.ch4_add_one_depthlevel_in_tree(tree)

  io.println("Expanded Tree: " <> recursive.to_string_tree(expaned_tree))
}
