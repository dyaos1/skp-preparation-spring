# skp preparation

### 1. 시나리오 준비

산기평 성과목표 지표 계획서 의 표를 직렬화 하여 DB에 저장

저장한 내용을 CRUD + 지령문 형태로 parsing하여 전달

### 2. 깃 브랜치 전략

브랜치 전략 = git flow (main - develop - feature)

커밋 룰 =

```markup
type: [#issuenumber]title
body

// type의 종류 : feat, fix, docs, refactor 등등
```

git kraken 이용 추천
if not:
```markup
git checkout -b feature/#10-make-history

// 작업 후
git commit -m feat: [#10]done
nothing to say
git push feature/#10-make-history

// git hub에서 pull request 마친 후
git checkout develop
git fetch -p
git pull origin develop

git checkout -b feature/#11-start-again
```
