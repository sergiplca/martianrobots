## Martian robots code challenge
### Statement of work
The surface of Mars can be modelled by a rectangular grid around which robots are
able to move according to instructions provided from Earth. You are to write a
program that determines each sequence of robot positions and reports the final
position of the robot.
A robot position consists of a grid coordinate (a pair of integers: x-coordinate followed
by y-coordinate) and an orientation (N, S, E, W for north, south, east, and west). A
robot instruction is a string of the letters "L", "R", and "F" which represent,
respectively, the instructions:
* Left: the robot turns left 90 degrees and remains on the current grid point.
* Right: the robot turns right 90 degrees and remains on the current grid point.
* Forward: the robot moves forward one grid point in the direction of the current
orientation and maintains the same orientation.

The direction North corresponds to the direction from grid point (x, y) to grid point (x,
y+1).
There is also a possibility that additional command types may be required in the
future and provision should be made for this.
Since the grid is rectangular and bounded (...yes Mars is a strange planet), a robot
that moves "off" an edge of the grid is lost forever. However, lost robots leave a robot
"scent" that prohibits future robots from dropping off the world at the same grid point.
The scent is left at the last grid position the robot occupied before disappearing over
the edge. An instruction to move "off" the world from a grid point from which a robot
has been previously lost is simply ignored by the current robot.

### Input
Input will be a json string containing:
* The upper-right coordinates of the rectangular world, the lower-left coordinates are assumed to be 0, 0.
* A list of robots with each one containing the starting position formed by the initial coordinates of the robot and an orientation (N, E, S, W), 
and a list of instructions ('L' for left, 'R' for right and 'F' for forward).
This is an example of how to list things you need to use the software and how to install them.
* Example:
  ```sh
  {
      "topCoordinates": "5 3",
      "robots": [
          {
              "robotPosition": "1 1 E",
              "instructions": "RFRFRFRF"
          }, {
              "robotPosition": "3 2 N",
              "instructions": "FRRFLLFFRRFLL"
          }, {
              "robotPosition": "0 3 W",
              "instructions": "LLFFFRFLFL"
          }
      ]
  }
  
### Output
Output will be a json string containing a list of the resulting position of all the robots:
* Example:
  ```sh
  [
      "1 1 E",
      "3 3 N LOST",
      "4 2 N"
  ]

###Use  
#####Dependencies
* Java 14
* Maven (will be used for retrieving the rest of dependencies)

#####Building and deploying
*
  ```sh
  mvn clean install
  mvn spring-boot:run
  
### Endpoints
#####POST
* `/v1/run-robots` Use for running robots on Mars surface. Body requires the input json
with the upper-right coordinates and the robots.


#####GET
* `/v1/get-scents` Use for retrieving the scents left by the robots.

#####H2 in-memory database UI
The H2 in memory database UI is accessible from the browser at http://localhost:8080/h2-ui where 
an execution history and scents can be checked. It gets deleted and recreated with every startup.
* Username: `sa`
* Password: empty

### Contact
* Sergi Plaza - plazacagigos@gmail.com
* Project link: https://github.com/sergiplca/martianrobots