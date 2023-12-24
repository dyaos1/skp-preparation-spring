import React, { useEffect } from 'react'

export default function Main() {
    return (
        <div>
            <h1>Hello from React!</h1>
            <input type="text" id="myText" />       // 텍스트 입력 받아서,
            <input type="submit" id="myButton" />   // submit 버튼을 누르면 파일 생성
            <div>
                <button id="go">go</button>         // go 버튼 누르면 exe 파일 실행
            </div>
        </div>
    )
  }