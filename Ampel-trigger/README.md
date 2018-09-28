# Ampel-Trigger


This shell switches your Ampel depending on the status of one or more GitLab jobs.

If any job failed the Ampel will turn red. All other conditions will be reflected
as green.

Just set the GitLab project ID's as well as the relevant branches in the script.
```
PROJECT_ID=(3 5 41 701)
BRANCHES=("master" "develop")
```
The script is supposed to run as the last stage of every pipeline of every project 
mentioned above.

Obviously the machine the runner is on and executes the script needs to be able to reach
the Raspberry Pi that actually switches the Ampel.

That's all folks!
