I am going to redo this project with what I have learnt over the years and from what people said people who have reviewed this during interviews.

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
