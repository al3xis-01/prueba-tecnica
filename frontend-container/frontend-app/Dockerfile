# Etapa de construcción (Build Stage)
FROM node:18-alpine AS builder

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo de configuración del paquete y el archivo de bloqueo de dependencias
COPY package*.json ./

# Instalar dependencias
# RUN npm install

# Copiar el resto de la aplicación
COPY . .

# Compilar la aplicación
#RUN npm run build --prod

# Etapa de Producción
FROM nginx:alpine

# Copiar los archivos de la etapa de construcción al directorio de Nginx
COPY --from=builder /app/dist/frontend-app/browser /usr/share/nginx/html
COPY /nginx.conf  /etc/nginx/conf.d/default.conf

# Puerto expuesto por Nginx
EXPOSE 80

# Comando para iniciar el servidor Nginx
CMD ["nginx", "-g", "daemon off;"]
