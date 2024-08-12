#!/bin/bash

for test in Alabama Alaska Arizona Arkansas California Colorado
do
    echo The next state is $test
done

echo "The last state we visited was $test"
test=Connecticut
echo "Wait, now we're visiting $test"

echo ----------------------------------
# 列表中被单引号包围的值会被 shell 当作一个单独的数据值
for test in I don't know if this'll work
do
    echo "word:$test"
done

echo ----------------------------------
# 如何解决单引号引发的问题
# 1. 使用转义字符
# 2. 使用双引号
for test in I don\'t know if "this'll" work
do
    echo "word:$test"
done

echo ----------------------------------
# for 命令用空格划分列表中的每个值
# 必须用双引号将列表中具有空格的值圈起来
for test in Nevada "New Hampshire" "New Mexico" "New York"
do
    echo "Now going to $test"
done

echo ----------------------------------
# 从变量中读取列表
list="Alabama Alaska Arizona Arkansas Colorado"
# 拼接赋值
list=$list" Connecticut"

for state in $list
do
    echo "Have you ever visited $state?"
done

echo ----------------------------------
# 从命令中读取值
# 默认情况下，bash shell 会将空格、制表符和换行符当作字段分符，如果 bash shell 在数据中看到这些字符中的任一个，它就假定这是列表中一个数据项的开始
# IFS 内部字段分隔符的环境变量定义了 bash shell的字段分隔符

# 设置换行符号为分隔符
# 改变 IFS 之前保存原来的 IFS 值
# $'\n' ANSI C 扩展字符(ANSI-C Quotin), https://www.gnu.org/software/bash/manual/html_node/ANSI_002dC-Quoting.html

OLDIFS=$IFS
IFS=$'\n'
file="states"
for state in $(cat $file)
do
    echo "Visit beautiful $state"
done
IFS=$OLDIFS

echo ----------------------------------
# 用通配符读取目录
# 也可以列出多个目录通配符
for file in /root/learning-* /root/tcpip
do
if [ -d "$file" ]
  then
      echo "$file is a directory"
      ls "$file"
  elif [ -f "$file" ]
  then
      echo "$file iss a file"
      cat "$file"
  fi
done


echo ----------------------------------
# C 语言风格的 for 循环
for (( i = 1, j = 10; i <= 10; i++, j--))
do
	#temp=$[ $i - $j ]
	#temp=$(expr $i - $j)
	(( temp = $i - $j ))
	echo "$i - $j = $temp"
done

echo ----------------------------------
#while 循环
var1=10
while [ $var1 -gt 0 ]
do
    echo "$var1"
    var1=$[ $var1 - 1 ]
done

echo ----------------------------------
# while循环使用多个测试命令 
# 每个测试命令都必须出现在单独的一行上
var2=10
while echo $var2
	[ $var2 -gt 0 ]
do
    echo "This is inside the loop"
    var2=$[ $var2 - 1 ]
done


echo ----------------------------------
# until 语句
# until 测试语句退出状态码不为0，循环体才会执行
var3=100
until [ $var3 -eq 0 ]
do
    echo Inside the loop: $var3
    var3=$[ $var3 - 25 ]
done

 
echo ----------------------------------
# 循环处理文件中的数据
IFS=$'\n'
for entry in $(cat /etc/passwd)
do
    echo "Values in $entry -"
    IFS=:
    for value in $entry
    do
        echo "   $value"
    done
done
IFS=$OLDIFS

echo ----------------------------------
for log in $(find /var/lib/docker/containers -name '*-json.log')
do
    echo "clean docker log: $log"
    cat /dev/null > $log
done

#while 循环
echo ----------------------------------
echo "find /var/lib/docker/containers -name '*-json.log'"
find /var/lib/docker/containers -name '*-json.log' | {
    while read -r docker_log
    do
        echo "clean docker log: $docker_log"	
    done
}

echo ----------------------------------
echo "grep -Ev '^#|^$' log.txt"
grep -Ev '^#|^$' log.txt | {
    while read -r docker_log; do
        echo "clean docker log: $docker_log"	
    done
}
