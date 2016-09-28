# Cricket match program

## Assumptions

1.  Client application gets control over output except per ball commentary.
2.  Client will enter probabilities in the order of results: dot ball, 1, 2, 3, 4, 5, 6, out.

## Structure of program

Batsman class is a model of a batsman. Innings class represents a single innings from a match. Innings is immutable once you call play. The order of batsmen cannot be changed and more batsmen cannot be added.

## How to run