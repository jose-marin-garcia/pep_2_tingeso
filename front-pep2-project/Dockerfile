# Usar una imagen base de Node
FROM node:16-alpine as builder

# Establecer el directorio de trabajo en el contenedor
WORKDIR /app

# Copiar los archivos de configuración del proyecto al directorio de trabajo
COPY package.json package-lock.json ./

# Instalar todas las dependencias
RUN npm install

# Copiar el resto de los archivos del proyecto al directorio de trabajo
COPY . .

# Construir la aplicación para producción
RUN npm run build

# Usar Nginx para servir el contenido estático
FROM nginx:alpine

# Copiar los archivos estáticos desde la etapa de construcción
COPY --from=builder /app/dist /usr/share/nginx/html

# Exponer el puerto 80 para el servidor web
EXPOSE 80
