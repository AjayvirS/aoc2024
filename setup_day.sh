#!/bin/bash
# Script to set up a new day's directory structure

if [ -z "$1" ]; then
    echo "Usage: ./setup_day.sh <day_number>"
    echo "Example: ./setup_day.sh 2"
    exit 1
fi

DAY=$(printf "%02d" $1)
DIR="day$DAY"

if [ -d "$DIR" ]; then
    echo "Directory $DIR already exists!"
    exit 1
fi

echo "Setting up $DIR..."
mkdir -p "$DIR"

# Create README.md
cat > "$DIR/README.md" << 'EOF'
# Day DAY_NUM

## Part 1

[Problem description will go here]

## Part 2

[Problem description will go here]

## Notes

- Add any notes or observations about the solution here
EOF

sed -i "s/DAY_NUM/$DAY/g" "$DIR/README.md"

# Create solution.py
cat > "$DIR/solution.py" << 'EOF'
#!/usr/bin/env python3
"""
Advent of Code 2024 - Day DAY_NUM
"""

def read_input(filename='input.txt'):
    """Read and parse the input file."""
    with open(filename, 'r') as f:
        return f.read().strip()

def part1(data):
    """Solve part 1 of the puzzle."""
    # TODO: Implement solution for part 1
    pass

def part2(data):
    """Solve part 2 of the puzzle."""
    # TODO: Implement solution for part 2
    pass

def main():
    """Main function to run the solution."""
    data = read_input()
    
    result1 = part1(data)
    print(f"Part 1: {result1}")
    
    result2 = part2(data)
    print(f"Part 2: {result2}")

if __name__ == '__main__':
    main()
EOF

sed -i "s/DAY_NUM/$DAY/g" "$DIR/solution.py"

# Create empty input.txt
echo "# Add your puzzle input here" > "$DIR/input.txt"

chmod +x "$DIR/solution.py"

echo "Successfully created $DIR structure!"
echo "You can now add your puzzle input to $DIR/input.txt"
