# data


## Robocode tanks 

In this folder, there are a few video and image files captured from
[Robocode](http://robocode.sourceforge.net/). These can be used to create a tracking problem.

* `still_1gray` folder contains a set of images of one tank. These images are
  intended to be used in a problem in which the goal is to get the coordinate
and/or the direction of the tank. The images are extracted from `still_1gray.ogg`.

* `still_2yellow` folder. Same as above but now with two tanks. Images are extracted from 
`still_2yellow.ogg`.

* `track_black1.ogg`: a video of a moving black tank. Intended to be used for
  extracting the trajectory.

* `track_black2.ogg`: a video of a moving black tank surrounded by more other
  tanks. For extracting the trajectory.

* `track_2green1.ogg`: a video of many moving tanks. Extract the trajectories
  of the two green tanks.

* `track_3green1.ogg`: a video of many moving tanks. Extract the trajectories
  of the three green tanks.

* `gam_markov_bot/, `hop_robot/`, `markov_bot/` are folders containing video
  clips of robots following predefined trajectory rules. The rules are described in 
  `bot2d/doc/`.

