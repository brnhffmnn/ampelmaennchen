#!/bin/sh

WGET=`which wget`
GITHUB_BASE=https://raw.githubusercontent.com/panzerfahrer/ampelmaennchen/master/server-raspberry

echo "Downloading service script"
$WGET "$GITHUB_BASE/service-daemons/debian" -O /etc/init.d/ampelmaennchen

echo "Downloading default configuration"
$WGET "$GITHUB_BASE/application.conf" -O /etc/default/ampelmaennchen/application.conf

echo "Preparing application directory"
mkdir /usr/local/ampelmaennchen

chmod +x /etc/init.d/ampelmaennchen
update-rc.d ampelmaennchen defaults

echo "Done."
echo ""
echo "Tasks to do next:"
echo " - edit the configuration in '/etc/default/ampelmaennchen/application.conf' "
echo " - place or link the compiled application in '/usr/local/ampelmaennchen/application.jar' "
echo ""
echo "Finally, run 'service ampelmaennchen start' to start the server."
