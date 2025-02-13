from enum import Enum
from typing import Self


class Position(Enum):
    LEFT = 1
    RIGHT = 2
    BOTH = 3


class Node:
    def __init__(self, value: int | float | str) -> None:
        self.value = value
        self.left = None
        self.right = None

    def __str__(self) -> str:
        return f"{self.value}"

    def add_left_child(self, value: int | float | str) -> None:
        self.left = Node(value)

    def add_right_child(self, value: int | float | str) -> None:
        self.right = Node(value)

    def remove_child(self, position: Position) -> None:
        if position == Position.LEFT:
            self.left = None
        elif position == Position.RIGHT:
            self.right = None
        elif position == Position.BOTH:
            self.left = None
            self.right = None


class Tree:
    def __init__(self, root: Node) -> None:
        self.root = root

    def __str__(self, current_node: Node = None, level: int = 0, child_type: str = "Root"):
        if current_node is None:
            current_node = self.root

        print(f"{' ' * level}Level: {level}, {child_type} Child: {current_node.value}")

        if current_node.left:
            self.__str__(current_node.left, level + 1, child_type="Left")
        if current_node.right:
            self.__str__(current_node.right, level + 1, child_type="Right")

        return "End of Tree reached"

    def add_node(self, parent_node_value: int | float | str, value: int | float | str, position: Position) -> None:
        parent_node = self.find_node(self.root, parent_node_value)
        if position == Position.LEFT and parent_node:
            parent_node.add_left_child(value)
        elif position == Position.RIGHT and parent_node:
            parent_node.add_right_child(value)

    def find_node(self, current_node: Node, value: int | float | str) -> Node | str:
        if current_node.value == value:
            return current_node
        elif current_node.right and current_node.right.value == value:
            return current_node.right
        elif current_node.left and current_node.left.value == value:
            return current_node.left
        else:
            if current_node.left:
                return self.find_node(current_node.left, value)
            if current_node.right:
                return self.find_node(current_node.right, value)

        return "Node not found"

    def remove_node(self, value: int | float | str) -> None:
        node_to_remove = self.find_node(self.root, value)

        if node_to_remove:
            parent_node, position = self.get_parent(self.root, node_to_remove.value)
            parent_node.remove_child(position)

    def get_parent(self, current_node: Node, value_to_remove: int | float | str) -> tuple[Node, Position]:
        if current_node.right and current_node.right.value == value_to_remove:
            return current_node, Position.RIGHT
        elif current_node.left and current_node.left.value == value_to_remove:
            return current_node, Position.LEFT

        if current_node.left:
            return self.get_parent(current_node.left, value_to_remove)
        if current_node.right:
            return self.get_parent(current_node.right, value_to_remove)


if __name__ == "__main__":
    node: Node = Node(42)
    tree: Tree = Tree(node)
    node.add_left_child(37)
    node.add_right_child(89)
    tree.add_node(37, 87, Position.LEFT)
    tree.add_node(87, 102, Position.RIGHT)
    tree.add_node(89, 1, Position.LEFT)
    tree.add_node(89, 2, Position.RIGHT)

    # print(tree.find_node(tree.root, 37))
    # print(tree.find_node(tree.root, 89))
    # print(tree.find_node(tree.root, 87))
    # print(tree.find_node(tree.root, 102))
    # print(tree.find_node(tree.root, 100))

    # tree.find_node(tree.root, 87).remove_child(Position.RIGHT)
    # tree.find_node(tree.root, 42).remove_child(Position.RIGHT)
    # tree.root.remove_child(Position.BOTH)
    # tree.find_node(tree.root, 89).remove_child(Position.BOTH)
    # tree.remove_node(89)
    # tree.remove_node(37)

    print(tree)
