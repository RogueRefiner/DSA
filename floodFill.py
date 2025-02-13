def floodfill(grid: list[list[str]], x: int, y: int, new_char: str, old_char: str = None) -> list[list[str]]:
    if old_char == None:
        old_char = grid[y][x]
    if old_char == new_char or grid[y][x] != old_char:
        return

    grid[y][x] = new_char

    if x-1 >= 0:
        floodfill(grid, x-1, y, new_char, old_char)
    if x+1 < len(grid[0]):
        floodfill(grid, x+1, y, new_char, old_char)
    if y-1 >= 0:
        floodfill(grid, x, y-1, new_char, old_char)
    if y+1 < len(grid):
        floodfill(grid, x, y+1, new_char, old_char)

    return grid


if __name__ == "__main__":
    grid: list[list[str]] = [[char for char in '..#############################......'],
                             [char for char in '..#...........................####...'],
                             [char for char in '..#..........####.............#..#...'],
                             [char for char in '..#..........#..#.............#..#...'],
                             [char for char in '..#..........####.............####...'],
                             [char for char in '..###.........................#......'],
                             [char for char in '....####..#####.....###########......'],
                             [char for char in '.......####...#######................']]
    grid = floodfill(grid, 3, 3, "x")

    for row in grid:
        print(f'{row}')
