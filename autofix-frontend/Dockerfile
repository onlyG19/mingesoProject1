FROM nginx:alpine

COPY /dist /usr/share/nginx/html
COPY /nginx/conf.d /etc/nginx/conf.d

# Exponer el puerto 3000 para que se pueda acceder a la aplicación desde el exterior
EXPOSE 3000

CMD ["nginx", "-g", "daemon off;"]