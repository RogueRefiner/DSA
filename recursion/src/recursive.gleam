import gleam/float
import gleam/int
import gleam/io
import gleam/result
import gleam/string

// Practice projects from the book "The recursive book of recursion"
// https://inventwithpython.com/#recursion

pub fn ch2_factorial(int: Int) -> Int {
  case int {
    1 -> 1
    _ -> int * ch2_factorial(int - 1)
  }
}

pub fn ch2_sum_series(int: Int) -> Int {
  case int {
    1 -> 1
    _ -> int + ch2_sum_series(int - 1)
  }
}

pub fn ch2_sum_powers_of_2(int: Int) -> Int {
  sum_of_powers_of_2_loop(int, 0)
}

fn sum_of_powers_of_2_loop(int: Int, aggregator: Int) -> Int {
  case int {
    0 -> aggregator
    _ ->
      sum_of_powers_of_2_loop(
        int - 1,
        aggregator
          + float.round(result.unwrap(int.power(2, int.to_float(int)), 0.0)),
      )
  }
}

pub fn ch3_concat(str_list: List(String)) -> String {
  concat_loop(str_list, "")
}

fn concat_loop(str_list: List(String), aggregator: String) -> String {
  case str_list {
    [] -> {
      aggregator
    }
    [head, ..tail] ->
      concat_loop(tail, string.append(to: aggregator, suffix: head))
  }
}

pub fn ch3_product(int_list: List(Int)) -> Int {
  product_loop(int_list, 1)
}

fn product_loop(int_list: List(Int), aggregator: Int) -> Int {
  case int_list {
    [] -> aggregator
    [head, ..tail] -> product_loop(tail, aggregator * head)
  }
}
