# SDE Online Assessment

## Overview

The build tool that is wired into the app is gradle. There are mainly two packages in this
app. The service package consists of all the services like the read service, write service
and the calculate service.

**Note** The test coverage is limited due to lack of time.

## Build and Execution
- Clone the repo and perform command `gradle build`. This will build binaries `sde-test-solution.tar`
 and `sde-test-solution.bin` in the `./build/distributions/` folder.
- Build docker image 
  `docker build -t spread-calculator:v2 .`
- Run docker image using the following command
`docker run -it -v <DIRECTORY WHERE input.json IS PRESENT>:/submission/input_data  
-v <DIRECTORY WHERE output.json NEEDS TO BE PRINTED>:/submission/output_data  spread-calculator:v2 ./input_data/sample_input.json ./output_data/final_output1.json
`

eg: `docker run -it -v /Users/jeevanvarughese/Documents/j_Interview/sde-test/sde-test-solution:/submission/input_data  -v /Users/jeevanvarughese/output:/submission/output_data  spread-calculator:v2 ./input_data/sample_input.json ./output_data/final_output1.json`

## Published in dockerhub
The image needs to be directly used. The following images can be downloaded from Dockerhub 
and then used.
- `thefenns/spread-calculator:v2`
