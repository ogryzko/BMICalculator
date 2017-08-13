module CalcInput.Panel exposing (..)

import Html exposing (..)
import Html.Attributes exposing (..)
import Html.Events exposing (onClick, onInput)
import Msgs exposing (Msg)


view : Html Msg
view =
    div [ class "panel" ]
        [ table []
            [ tbody []
                [ age
                , gender
                ]
            ]
        , table []
            [ tbody []
                [ height
                , weight
                ]
            ]
        , calculateBtn
        ]


age : Html Msg
age =
    tr []
        [ td [ attribute "width" "50" ]
            [ text "Возраст" ]
        , td [ align "right", attribute "width" "140" ]
            [ input [ type_ "text", onInput Msgs.Age ]
                []
            ]
        , td [ attribute "width" "73" ]
            [ text " " ]
        ]


gender : Html Msg
gender =
    tr []
        [ td []
            [ text "Пол" ]
        , td [ align "right" ]
            [ label [ for "csex1" ]
                [ input [ attribute "checked" "", id "csex1", name "csex", type_ "radio", onClick (Msgs.SetGender "Male") ]
                    []
                , text "Муж."
                ]
            , text "  "
            , label [ for "csex2" ]
                [ input [ id "csex2", name "csex", type_ "radio", onClick (Msgs.SetGender "Female") ]
                    []
                , text "Жен."
                ]
            ]
        , td []
            [ text " " ]
        ]


height : Html Msg
height =
    tr []
        [ td [ attribute "width" "50" ]
            [ text "Рост" ]
        , td [ align "right", attribute "width" "140" ]
            [ input [ type_ "text", onInput Msgs.Height ]
                []
            ]
        , td [ attribute "width" "73" ]
            [ text "см" ]
        ]


weight : Html Msg
weight =
    tr [ id "metricweight" ]
        [ td []
            [ text "Вес" ]
        , td [ align "right" ]
            [ input [ type_ "text", onInput Msgs.Weight ]
                []
            ]
        , td []
            [ text "кг" ]
        ]


calculateBtn : Html Msg
calculateBtn =
    table [ attribute "width" "263" ]
        [ tbody []
            [ tr []
                [ td [ attribute "width" "73" ]
                    [ text " " ]
                , td [ attribute "width" "190" ]
                    [ button [ onClick Msgs.Calculate ] [ text "Посчитать" ]
                    ]
                ]
            ]
        ]
