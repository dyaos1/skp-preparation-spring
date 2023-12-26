import os
from pathlib import Path
import json

FILEPATH = os.path.join((Path(os.getcwd()).parent), "reel/test.json")
RESULTPATH = os.path.join((Path(os.getcwd())), "result.txt")

print(FILEPATH)

fieldBuilder = dict()
tableBuilder = dict()
resultText = ""

with open(FILEPATH, 'r') as jsonfile:
    data = json.load(jsonfile)
    for i in data['fields']:
        fieldBuilder[i['key']] = i['value']

    for i in data['tables']:
        tableBuilder[i['key']] = i['value']

    resultText = "제목: " + fieldBuilder["{{title}}"] + "\n" + \
        "전략목표: " + fieldBuilder["{{sGoal}}"] + "\n" + \
        "성과목표: " + fieldBuilder["{{pGoal}}"] + "\n" + \
        "단계별 성과목표: "+"/".join(tableBuilder["{{stage}}"])
    
    print(resultText)

with open(RESULTPATH, 'w', encoding='utf-8') as resultfile:
    resultfile.write(resultText)