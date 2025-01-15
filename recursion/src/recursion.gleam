import gleam/io
import gleam/string

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

pub fn main() {
  let list: List(Int) = [1, 2, 3, 4, 5]
  let str: String = "Hello World!"
  let string_list: List(String) = string.split(str, on: "")

  io.debug(sum(list, 0))

  io.debug(string_list)
  io.debug(reverse_string(string_list))
}
