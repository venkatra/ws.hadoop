#!/bin/bash

while read line
do
 for word in $line 
 do
 	if [ -n $word ]; then
		wcount=`echo $word | wc -m`;
		wlength=`expr $wcount - 1`;
		letter=`echo $word | head -c1`;
		echo -e "$letter\t$wlength";
fi
	done
done

echo "Hello" "world!!"
exit 0