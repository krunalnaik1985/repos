FROM ubuntu:latest

# Update
RUN apt-get update -y

# install python
RUN apt-get install -y python-pip python-dev build-essential

# Bundle app source
COPY . /src/

# change work directory
WORKDIR /src

# Install app dependencies
RUN pip install -r requirements.txt

# expose port outside
EXPOSE  8000

# run JS project
CMD ["python", "/src/my_js_project.py", "-p 8000"]
