#!/bin/bash
#set -x

# test 数值比较
# 不能在 test 命令中使用浮点值
echo -------------------------
value1=5.55

echo "The test value1 is $value1"
if [ $value1 -gt 5 ]
then
	echo "The test value $value1 is greater than 5"
fi

echo -------------------------
# test 字符串比较
value2=baseball
value3=hockey
# test 命令中的大于号和小于号需要转义，否则会被shell当作重定向符号
if [ $value2 > $value3 ]
then
	#  [ $value2 > $value3 ] 测试命令的大于号会被当作重定向符号，因此 test 测试命令会创建 hockey 文件
	rm $value3
	echo "$value2 is greater than $value3"
else
	echo "$value2 is less than $value3"
fi

# 转义大于号
if [ $value2 \> $value3 ]
then
	echo "$value2 is greater than $value3"
else
	echo "$value2 is less than $value3"
fi

val4=testing
val5=''

if [ -n $val4 ]
then
	echo "The string '$val4' is not empty"
else
	echo "The string '$val4' is empty"
fi

if [ -z $val5 ]
then
	echo "The string '$val5' is empty"
else
	echo "The string '$val5' is not empty"
fi

echo -------------------------
# test 文件比较
jump_directory=/home/bbb
if [ -d $jump_directory ]
then
	echo "The $jump_directory directory exists"
	ls $jump_directory
fi

# (( expression ))
# 可以在 if 语句中使用双括号命令，也可以在脚本的普通命令中赋值使用。
val1=10
if (( $val1 ** 2 > 90 ))
then
	(( val2 = $val1 ** 2 ))
	echo "The square of $val1 is $val2"
fi

# case 命令

case $USER in
	root | bbb)
		echo "Welcome, $USER"
		echo "Please enjoy your visit"
		;;
	testing)
		echo "Special testing account"
		;;
	jessica)
		echo "Do not forget to log off when you're done"
		;;
	*)
		echo "Sorry, you are not allowd here"
		;;
esac
