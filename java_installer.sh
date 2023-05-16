clear
echo "$(tput setaf 10)[Sollicitus Bot]:$(tput setaf 7) Bem vindo ao instalador Java!"
sleep 2
clear
echo "$(tput setaf 10)[Sollicitus Bot]:$(tput setaf 7) Verificando se você já tem o java instalado em sua máquina..."
sleep 2
java -version
if [ $? = 0 ]
then
	echo "$(tput setaf 10)[Sollicitus Bot]:$(tput setaf 7) Você já tem o java instalado!"
	sleep 2
	clear

else
	echo "$(tput setaf 10)[Sollicitus Bot]:$(tput setaf 7) Você não possui nenhuma versão do java instalada! Gostaria de instalar o java agora? [s/n]"

read get
	if [ \"$get\" == \"s\" ];

	then
		sudo apt install openjdk-17-jre -y
		clear
	else
		echo "$(tput setaf 10)[Sollicitus Bot]:$(tput setaf 7) Você optou por não instalar, até a próxima!"
		sleep 2
		clear
	fi

fi


