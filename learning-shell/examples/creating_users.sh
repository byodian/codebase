#!/bin/bash

# process new user accounts
input="users.cvs"
while IFS=',' read -r userid name
do
    grep -wq $userid /etc/passwd && [ -d /home/$userid  ] && continue
    echo "adding $userid"
    useradd -c "$name" -m $userid
done < "$input"

tail -n 3 /etc/passwd
