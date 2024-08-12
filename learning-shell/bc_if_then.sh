#!/bin/bash

# bc
echo -----------------------------------------
var1=10.46
var2=43.67
var3=33.2
var4=71

var5=$(bc << EOF
# options
scale=4
# expressions
a1 = ( $var1 * $var2 )
b1 = ( $var3 * $var4 )
# statement
a1 + b1
EOF
)
echo The final answer for this mess is $var5

echo -----------------------------------------
read -r -p "请输入用户名称: " testuser

# if-then
# -w 匹配整个单词
if grep -wq $testuser /etc/passwd
then
	echo "The user $testuser exists on this system"
else
	echo "The user $testuser does not exist on this system"
fi


echo -----------------------------------------
# -w 匹配整个单词
# 嵌套if-then 
if grep -wq $testuser /etc/passwd
then
	echo "The user $testuser exists on this system"
else
	echo "The user $testuser does not exist on this system"
	if ls -d /home/$testuser 2> /dev/null
	then
		echo "However, $testuser has a directory"
	fi
fi

echo

echo -----------------------------------------
# if-then-elif
if grep -wq $testuser /etc/passwd
then
	echo "The user $testuser exists on this system"
elif ls -d /home/$testuser 2> /dev/null
then
	echo "The user $testuser does not exist on this system"
	echo "However, $testuser has a directory"
else
	echo "The user $testuser does not exist on this system"
	echo "And, $testuser does not have a directory"
fi

echo
echo "The end"
