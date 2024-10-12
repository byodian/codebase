#!/bin/bash

# 设置要搜索的目录
search_dir=/data/docker

# 新的 IP 地址
new_ip=172.29.240.5
new_server_addr=172.29.240.5:8848
new_namespace=5c972876-b3ea-4a4e-a539-7332c66e43eb

# 使用 find 查找所有 bootstrap.yaml 或 bootstrap.yml 文件
find $search_dir -type f \( -name "bootstrap.yaml" -o -name "bootstrap.yml" \) | while read file; do
  echo "Processing file: $file"

  # 替换 ip 后的 IP 地址
  sed -i -E "s/(ip: )[0-9]+\.[0-9]+\.[0-9]+\.[0-9]+/\1$new_ip/g" "$file"

  echo "Updated IP addresses in $file"

  # 替换 server-addr 后的 IP 地址
  sed -i -E "s/(server-addr: ).*/\1$new_server_addr/g" "$file"

  echo "Updated IP server addr in $file"

  # 替换 namespace 后的任意值
  sed -i -E "s/(namespace: ).*/\1$new_namespace/g" "$file"

  echo "Updated namespace in $file"


done

echo "All files processed successfully."

