# its a minimal alpine linux os w/ Nodejs v18, all the tools for a js/ts application -> used in build stage to compline the application
FROM node:18-alpine AS build
WORKDIR /app
COPY . .
RUN npm install && npm run build

# since nginx:alpine has a default entrypoint configured in the base image we don't need to define it
# nginx web server pre-installed and configured -> defualt settings to service static web content
FROM nginx:alpine 
COPY --from=build /app/dist/* /usr/share/nginx/html
EXPOSE 80