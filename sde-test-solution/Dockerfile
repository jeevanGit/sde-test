FROM hax0rcorp/java11gradle

CMD ["mkdir", "/submission"]

CMD ["mkdir", "/submission/input_data"]

CMD ["mkdir", "/submission/output_data"]

WORKDIR /submission

#For Test
#COPY /input_data/* .

COPY ./build/distributions/sde-test-solution.tar .

RUN ["/bin/bash","-c", "tar -xvf sde-test-solution.tar"]


ENTRYPOINT ["./sde-test-solution/bin/sde-test-solution"]
