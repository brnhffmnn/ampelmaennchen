# Collection of scripts that play well with the server

## Jenkins

### [jenkins_job-states.groovy](jenkins_job-states.groovy)
 
When run by a Jenkins job, it will notify all job states to Ampelmaennchen and by that trigger the lights.


## Gitlab CI

### [gitlab-ci_pipline-trigger.sh](gitlab-ci_pipline-trigger.sh)

This shell switches your Ampel depending on the status of one or more GitLab jobs.

If any job failed the Ampel will turn red. All other conditions will be reflected
as green.

Just set the GitLab project ID's as well as the relevant branches, hostnames and tokens in the script:

```
PROJECT_ID=(3 5 41 701)
BRANCHES=("master" "develop")

AMPEL_HOST=your-ampel.local

GITLAB_HOST=gitlab.example.com
GITLAB_PRIVATE_TOKEN=xyz1234
GITLAB_COOKIE_SESSION=xyz1234
```

The script is supposed to run as the last stage of every pipeline of every project 
mentioned above.

Obviously the machine the runner is on and executes the script needs to be able to reach
the Raspberry Pi that actually switches the Ampel.