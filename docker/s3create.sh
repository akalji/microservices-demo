#!/usr/bin/env bash
set -x
awslocal s3 mb s3://resource-bucket
awslocal s3 mb s3://resource-staging-bucket
set +x