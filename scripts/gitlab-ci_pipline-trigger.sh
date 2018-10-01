#!/bin/bash

# Iterating through last master & develop pipeline of selected projects.
# Traffic light turns red when it finds at least one pipeline that fialed.

# run 'brew install jq' to install ./jq first

PROJECT_ID=(3 5 41 701)
BRANCHES=("master" "develop")

AMPEL_HOST=your-ampel.local

GITLAB_HOST=gitlab.example.com
GITLAB_PRIVATE_TOKEN=xyz1234
GITLAB_COOKIE_SESSION=xyz1234

setRedLight()
{
  curl "http://$AMPEL_HOST/manually/green/off"
  curl "http://$AMPEL_HOST/manually/red/on"
}


setGreenLight()
{
  curl "http://$AMPEL_HOST/manually/red/off"
  curl "http://$AMPEL_HOST/manually/green/on"
}


checkLastPipelineStatus()
{
  ## Latest Pipeline Info
  local RESPONSE=$(curl -s "https://$GITLAB_HOST/api/v4/projects/$1/pipelines?private_token=$GITLAB_PRIVATE_TOKEN&page=1&per_page=1&ref=$2" \
     -H 'Cookie: _gitlab_session=$GITLAB_COOKIE_SESSION' | jq .[].status)

  if [ "$RESPONSE" == "\"failed\"" ]
  then
    echo -e "\033[0;31m FAILED \033[0m"

    # switch light Ampel to Red here
    setRedLight

    exit 0
  fi
}

checkAllPipelines()
{
  for PROJECT in "${PROJECT_ID[@]}"; do
    for BRANCH in "${BRANCHES[@]}"; do
      echo -e "\033[0mProject-ID: $PROJECT / Branch: $BRANCH"
      checkLastPipelineStatus $PROJECT $BRANCH
    done
  done
}

checkAllPipelines

# switch light Ampel to Green here
setGreenLight

exit 0
