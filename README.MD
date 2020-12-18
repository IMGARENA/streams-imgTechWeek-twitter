##To run app locally

Generate AWS credentials:

`aws sts assume-role --role-arn arn:aws:iam::154882459970:role/streams-sandbox-developer --role-session-name Alex`

...and then put them in environmental variables on the run configuration:
```
AWS_PROFILE=profile:sandbox;
AWS_REGION=eu-west-1;
AWS_ACCESS_KEY_ID=xxxxx
AWS_SECRET_ACCESS_KEY=zzzzz
AWS_SESSION_TOKEN=yyyyyy
twitter.hashtag=#imgTechWeek #ufc
```


##Deploy the image to AWS
Create Docker image (using [Paketo](https://paketo.io/)):

`pack build imgtechweek/twitter`

Login Docker to AWS:

`aws ecr get-login-password --region eu-west-1 | docker login --username AWS --password-stdin aws_account_id.dkr.ecr.eu-west-1.amazonaws.com`

Tag the image:

`docker tag 154882459970.dkr.ecr.eu-west-1.amazonaws.com/imgtechweek imgtechweek/twitter`

Push image to ERC:

`docker push 154882459970.dkr.ecr.eu-west-1.amazonaws.com/imgtechweek`