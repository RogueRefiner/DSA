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

    def get_all_nodes(self, current_node: Node, nodes: list[Node] = None) -> list[Node] | None:
        if nodes is None:
            nodes = []

        nodes.append(current_node.value)

        if current_node.left:
            self.get_all_nodes(current_node.left, nodes)
        if current_node.right:
            self.get_all_nodes(current_node.right, nodes)

        return nodes

    def __build_heap_tree(self, nodes: list[Node]) -> Self:
        root_node = Node(nodes.pop(0))
        len_nodes = len(nodes)
        heap_tree = Tree(root_node)

        while len(nodes) >= len_nodes//2 + 1:
            new_node = Node(nodes.pop(0))
            if root_node.left is None:
                heap_tree.add_node(root_node.value, new_node.value, Position.LEFT)
                root_node.add_left_child(new_node.value)
                parent_node = new_node

            elif parent_node.left is None:
                heap_tree.add_node(parent_node.value, new_node.value, Position.LEFT)
                parent_node.add_left_child(new_node.value)
            elif parent_node.right is None:
                heap_tree.add_node(parent_node.value, new_node.value, Position.RIGHT)
                parent_node.add_right_child(new_node.value)
            else:
                parent_node = parent_node.left
                if parent_node.left is None:
                    heap_tree.add_node(parent_node.value, new_node.value, Position.LEFT)
                    parent_node.add_left_child(new_node.value)
                elif parent_node.right is None:
                    heap_tree.add_node(parent_node.value, new_node.value, Position.RIGHT)
                    parent_node.add_right_child(new_node.value)

        parent_node = heap_tree.root
        while len(nodes) > 0:
            new_node = Node(nodes.pop(0))
            if parent_node.left is None:
                parent_node.add_left_child(new_node.value)
            elif parent_node.right is None:
                parent_node.add_right_child(new_node.value)
            else:
                if parent_node == heap_tree.root:
                    parent_node = parent_node.right
                else:
                    parent_node = parent_node.left

                if parent_node.right is None:
                    parent_node.add_right_child(new_node.value)
                elif parent_node.left is None:
                    parent_node.add_left_child(new_node.value)

        return heap_tree

    def max_heap_sorting(self, current_node: Node = None) -> Self:
        if current_node is None:
            current_node = self.root

        nodes = sorted(self.get_all_nodes(current_node), reverse=True)
        return self.__build_heap_tree(nodes)

    def min_heap_sorting(self, current_node: Node = None) -> Self:
        if current_node is None:
            current_node = self.root

        nodes = sorted(self.get_all_nodes(current_node))
        return self.__build_heap_tree(nodes)

    def get_depth(self, current_node: Node = None, level: int = 0, max_depth: int = 0) -> int:
        if current_node is None:
            current_node = self.root

        if level > max_depth:
            max_depth = level

        if current_node.left:
            max_depth = self.get_depth(current_node.left, level+1, max_depth)
        if current_node.right:
            max_depth = self.get_depth(current_node.right, level+1, max_depth)

        return max_depth

    def print_preorder_traversal(self, current_node: Node = None) -> str:
        return self.__str__(current_node)

    def print_postorder_traversal(self, current_node: Node, level: int = 0, child_type: str = "Root") -> str:
        if current_node is None:
            return "End of Tree reached"

        self.print_postorder_traversal(current_node.left, level+1, "Left")
        self.print_postorder_traversal(current_node.right, level+1, "Right")
        print(f'{" "*level}Level: {level}, {child_type} Child: {current_node}')

    def print_inorder_traversal(self, current_node: Node = None, level: int = 0, child_type: str = "Root") -> str:
        if current_node is None:
            return "End of Tree reached"

        self.print_inorder_traversal(current_node.left, level+1, "Left")
        print(f'{" "*level}Level: {level}, {child_type} Child: {current_node}')
        self.print_inorder_traversal(current_node.right, level+1, "Right")


if __name__ == "__main__":
    node: Node = Node(42)
    tree: Tree = Tree(node)
    node.add_left_child(37)
    node.add_right_child(89)
    tree.add_node(37, 87, Position.LEFT)
    tree.add_node(89, 1, Position.LEFT)
    tree.add_node(89, 2, Position.RIGHT)
    tree.add_node(87, 102, Position.RIGHT)
    tree.add_node(102, 277, Position.RIGHT)
    tree.add_node(102, 96, Position.LEFT)
    tree.add_node(96, 72, Position.LEFT)

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
    # print(tree.get_depth())
    # print(tree.min_heap_sorting())
    # print(tree.max_heap_sorting())
    print(tree)

    # print("Traversal: ")
    # tree.print_preorder_traversal()
    # tree.print_postorder_traversal(tree.root)
    # tree.print_inorder_traversal(tree.root)
