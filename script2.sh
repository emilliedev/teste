java -version

if [ $? = 0 ]
then
	find -name 'sollicitusCollector.jar' | grep -q sollicitusCollector.jar

	if [ $? = 0 ];
		then
			echo "$(tput setaf 10)[Sollicitus bot]:$(tput setaf 7) Gostaria de iniciar a aplicação Sollicitus Collector? [s/n]"
	read get
		if [ \"$get\" == \"s\" ];
			then
			clear
			find -name sollicitusCollector.jar -exec java -jar {} \;
		fi
	else
		echo "$(tput setaf 10)[Sollicitus bot]:$(tput setaf 7) Gostaria de instalar o Sollicitus Collector? [s/n]"
	read get
		if [ \"$get\" == \"s\" ];
			then
			git clone https://github.com/Projeto-grupo-05/jar-executavel.git
			clear

			echo "$(tput setaf 10)[Sollicitus bot]:$(tput setaf 7) Sollicitus Collector instalado com sucesso!"
			sleep 2
			echo "$(tput setaf 10)[Sollicitus bot]:$(tput setaf 7) Gostaria de iniciar a aplicação Sollicitus Collector? [s/n]"

			read get
			if [ \"$get\" == \"s\" ];
				then
				clear
				find -name sollicitusCollector.jar -exec java -jar {} \;
			fi
		fi
	fi

else
	echo "$(tput setaf 10)[Sollicitus bot]:$(tput setaf 7) O java não foi encontrado!"
	sleep 2
	echo "$(tput setaf 10)[Sollicitus bot]:$(tput setaf 7) Gostaria de instalar o java? [s/n]"
read get
	if [ \"$get\" == \"s\" ];
	then
		ls | grep "java_installer.sh"
		bash java_installer.sh
		clear
	fi
fi


