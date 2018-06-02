#!/usr/bin/env bash

curl -H "Content-Type: application/json" -d '{ "sender":"me", "recipient":"you", "body":"hello" }' http://localhost:18190/message/send
