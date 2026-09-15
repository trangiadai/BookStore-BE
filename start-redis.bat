@echo off
docker start local-redis 2>nul || docker run -d --name local-redis -p 6379:6379 redis:latest
echo Redis started on port 6379
pause