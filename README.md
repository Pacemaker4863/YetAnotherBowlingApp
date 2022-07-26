# Yet Another Bowling Application

Description
-----------
Yet Another Bowling Application calculates the score of a bowling game

- Classical bowling in 10 frames of 10 pins with 2 balls per frame
- Archaic bowling in 5 frames of 15 pins with 3 balls per frame

Run the application
-------------------
1. Change directory

````shell
cd [location of the YetAnotherBowlingApp folder]/core
````

2. Build the docker image

````docker
docker build -f .\Dockerfile --progress plain -t bowling-app-calc:0.7.2 .
````
3. Run the container

````docker
docker run -it bowling-app-calc:0.7.2
````


