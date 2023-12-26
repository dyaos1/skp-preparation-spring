import React, { useEffect, useState } from 'react'

export default function Main() {

    const [ resultJson, setResult ] = useState("")
    const [ resultJson2, setResult2 ] = useState({})

    useEffect(() => {
        const setButton = document.getElementById('myButton')
        // const textInput = document.getElementById('myText')
        setButton.addEventListener('click', () => {
            // const textValue = resultArea.value
            const jsonValue = resultJson2
            window.electronAPI.setTextValue(jsonValue)
        })

        const resultArea = document.getElementById('json-here')
        const retrieveButton = document.getElementById('get-report')
        retrieveButton.addEventListener('click', () => {
            fetch("http://localhost:8080/generator?reportId=1&generatedBy=me",
                {
                    method: "GET",
                    headers: { "Content-Type": "application/json"},
                }
            ).then((response) => {
                return response.json()
            })
            .then((res) => {
                setResult(JSON.stringify(res))
                setResult2(res)
            }).then(() => {
                console.log(resultJson)
                resultArea.innerHTML = resultJson
            })
        })
    })

    return (
        <div>
            <h1>Hello from React!</h1>
            <div>
                <label for="report-id">보고서번호</label>
                <input type="text" id="report-id"/>
            </div>
            <div>
                <label for="generated-by">작성자</label>
                <input type="text" id="generated-by" />
            </div>
            <div>
                <input type="submit" id="get-report" value="retrieve" />
            </div>

            <div>
                <input type="text" id="myText" /> 
                <input type="submit" id="myButton" />
            </div>
            <div>
                <button id="go">go</button> 
            </div>
            
            <div>
                <p id="json-here"></p>
            </div>
        </div>
    )
  }