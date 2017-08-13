module Models exposing (..)

import Date exposing (Date)
import RemoteData exposing (WebData)


type alias Model =
    { history : WebData (List HistoryItem)
    , input : Input
    }


initialModel : Model
initialModel =
    { history = RemoteData.NotAsked
    , input = initialInput
    }


initialInput : Input
initialInput =
    { age = 0
    , date = Date.fromTime 0
    , gender = "Male"
    , height = 0.0
    , weight = 0.0
    , isSubmitted = False
    }


type alias Input =
    { age : Int
    , date : Date
    , gender : Gender
    , height : Float
    , weight : Float
    , isSubmitted : Bool
    }


type alias Gender =
    String


type alias DateString =
    String


type alias HistoryItem =
    { id : Int
    , date : DateString
    , age : Int
    , gender : Gender
    , height : Float
    , weight : Float
    , bmi : Float
    , pi : Float
    , kind : String
    }
