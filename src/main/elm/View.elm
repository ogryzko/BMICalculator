module View exposing (..)

import CalcInput.Panel
import Date exposing (Date)
import History.List
import Output.Panel
import Html exposing (..)
import Html.Attributes exposing (..)
import Models exposing (Input, Model)
import Msgs exposing (Msg)


view : Model -> Html Msg
view model =
    div [class "container"]
    [
        div [ id "content", class "row" ]
            [ Output.Panel.view model.result
            , viewLeftInput model.input
            ]
            ,History.List.view model.history
     ]


viewLeftInput : Input -> Html Msg
viewLeftInput input =
    div [ class "col" ]
        [ CalcInput.Panel.view
        , validation input
        ]



validation : Input -> Html Msg
validation input =
    if input.isSubmitted then
        let
            cond =
                [ ( (input.age <= 120) && (input.age > 0), "Пожалуйста, введите возраст от 0 до 120 лет" )
                , ( (input.height < 300) && (input.height > 0), "Пожалуйста, введите рост от 0 до 300 сантиметров" )
                , ( (input.weight < 570) && (input.weight > 0), "Пожалуйста, введите вес от 0 до 570 килограммов" )
                ]
        in
        div [] (List.map conditionAndMessageToErrorDiv cond)
    else
        div [] []


conditionAndMessageToErrorDiv : ( Bool, String ) -> Html Msg
conditionAndMessageToErrorDiv ( cond, message ) =
    if cond then
        div [] []
    else
        div [ style [ ( "color", "red" ) ] ] [ text message]
