# Context
Our problem is set in the planet of Lengaburu, in the distant distant galaxy of Tara B. And it's the finals of the Intergalactic T20 Cup!

Lengaburu and Enchai, neighbours and fierce rivals, are fighting it out for the title. Lengaburu's star batsman Kirat Boli is at the crease. Can he win it for Lengaburu? Write code to simulate the last 4 overs of the match.

# Problem 1: The Last Four
It's the last 4 overs of the match. **Lengaburu needs 40 runs to win and with 3 wickets left**. Each player has a different probability for scoring runs. Your coding problem is to simulate the match, ball by ball

The match simulation will require you to use a weighted random number generation based on probability to determine the runs scored per ball. For this randomizer, you can use any external library of your choice (if you wish to, the choice is yours).

## Prayer Probabilities

| | DOT BALL | 1 | 2 | 3 | 4 | 5 | 6 | OUT
--- | --- | --- | --- | --- | --- | --- | --- | ---
Kirat Boli | 5% | 30% | 25% | 10% |15% | 1% | 9% | 5%
N.S Nodhi | 10% | 40% | 20% | 5% | 10% | 1% | 4% | 10%
R Rumrah | 20% | 30% | 15% | 5% | 5% | 1% | 4% | 20%
Shashi Henra | 30% | 25% | 5% | 0% | 5% | 1% | 4% | 30%


## Rules of Cricket

1. Batsmen change strike end of every over. They also change strike when they score a 1,3 or 5
2. When a player gets out, the new player comes in at the same position.
3. Assume only legal balls are bowled (no wides, no no-balls etc..). Therefore an over is always 6 balls

## Sample output

```
Lengaburu won by 1 wicket and 2 balls remaining

Kirat Boli - 12 (6 balls)
NS Nodhi - 25 (11 balls)
R Rumrah - 2* (3 balls)
Shashi Henra - 2* (2 balls)

Sameple Commentary

4 overs left. 40 runs to win

0.1 Kirat Boli scores 1 run
0.2 NS Nodhi scores 4 runs
0.3 NS Nodhi scores 1 run
0.4 Kirat Boli scores 2 runs
0.5 Kirat Boli scores 3 runs
0.6 NS Nodhi scores 1 run

3 overs left. 28 runs to win

1.1 NS Nodhi scores 2 runs
...
```

*Note*: You can assume both Kirat Boli and NS Nodhi are batting on 0* when the simulation begins

# Problem 2: The Tie Breaker

The final has resulted in a tie! Just like '07. Now the result will be decided by a one over tie breaker. 2 batsmen, 6
balls, who will win?

## Prayer Probabilities

### Legaburu

| | DOT BALL | 1 | 2 | 3 | 4 | 5 | 6 | OUT
--- | --- | --- | --- | --- | --- | --- | --- | ---
Kirat Boli | 5% | 10% | 25% | 10% | 25% | 1% | 14% | 10%
N.S Nodhi | 5% | 15% | 15% | 10% | 20% | 1% | 19% | 15%

### Enchai

| | DOT BALL | 1 | 2 | 3 | 4 | 5 | 6 | OUT
--- | --- | --- | --- | --- | --- | --- | --- | ---
DB Vellyers 5% | 10% | 25% | 10% | 25% | 1% | 14% | 10%
H Mamla | 10% | 15% | 15% | 10% | 20% | 1% | 19% | 10%

*Note:* Probability chart for Kirat Boli and NS Nodhi have changed from problem 1 (to reflect a 1 over match)

## Sample output
```
Enchai won with 4 balls remaining

Lengaburu
Kirat Boli - 4 (2 balls)
NS Nodhi - 0* (0 balls)

Enchai
DB Vellyers - 1* (1 ball)
H Mamla - 4* (1 balls)

Sample Commentary

Lengaburu innings:
0.1 Kirat Boli scores 4 runs!
0.2 Kirat Boli gets out! Lengaburu all out

Enchai innings:
0.1 DB Vellyers scores 1 run
0.2 H Mamla scores 4 runs! Enchai wins!
```

*Note:* Assume Lengaburu always bats first

# Solution

Update: I am going to redo this project with what I have learnt over the years and from what people said people who have reviewed this during interviews.

##Innings States
- `SETUP`: Default state once object is created. You can call `addbatsman()` only here. Once this state is exited, you cannot return to it.
- `READY` : State after calling `ready()`. You can run the simulation now.
- `TESTING` : State after calling `test (int [] testArray)`. Similar to `ready()` but skips the randomness. 
- `ALLOUT` : State if team gets all out.
- `BALLSOVER` : State if team finish the overs. (without achieving target if second innings)
- `TARGETACHIEVED` : State if team makes the target. (if second innings).

##Running the program
Run *java bin/Problem2* and *java bin/Problem3* for solutions to problems 2 and 3 respectively of the cricket set.

To run tests, run *java bin/TestBatsman*, *java bin/TestInningsControllerAndBroadcaster* and *java bin/TestInnings*. Make sure junit and matcher jars are added to the java classpath.

##Other information
TestInnings class tests the program output vs expected correct outputs while the other test classes test each class.
