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

pub fn ch4_reverse_inorder_traverse(tree: Tree) -> String {
  case tree {
    Leaf -> ""
    Branch(root, left, right) -> {
      ch4_reverse_inorder_traverse(right)
      io.debug(int.to_string(root))
      ch4_reverse_inorder_traverse(left)
    }
  }
}

pub fn ch4_add_one_depthlevel_in_tree(tree: Tree) -> Tree {
  case tree {
    Leaf -> Branch(-1, Leaf, Leaf)
    Branch(root, left, right) -> {
      Branch(
        root,
        ch4_add_one_depthlevel_in_tree(left),
        ch4_add_one_depthlevel_in_tree(right),
      )
    }
  }
}

pub type Tree {
  Leaf
  Branch(root: Int, left: Tree, right: Tree)
}

pub fn insert(tree: Tree, value: Int) -> Tree {
  case tree {
    Leaf -> Branch(value, Leaf, Leaf)
    Branch(root, left, right) ->
      case value < root {
        True -> Branch(root, insert(left, value), right)
        False -> Branch(root, left, insert(right, value))
      }
  }
}

pub fn to_string_tree(tree: Tree) -> String {
  case tree {
    Leaf -> "Leaf"
    Branch(root, left, right) ->
      "Node("
      <> int.to_string(root)
      <> ", "
      <> to_string_tree(left)
      <> ", "
      <> to_string_tree(right)
      <> ")"
  }
}

pub fn ch5_binary_search(tree: Tree, to_find: Int) -> String {
  case tree {
    Leaf -> "Not Found: " <> int.to_string(to_find)
    Branch(root, left, right) -> {
      case root == to_find {
        True -> "Found: " <> int.to_string(to_find)
        False -> {
          case root < to_find {
            True -> ch5_binary_search(right, to_find)
            False -> ch5_binary_search(left, to_find)
          }
        }
      }
    }
  }
}
